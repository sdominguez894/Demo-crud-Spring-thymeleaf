(() => {
    const gridSelector = '.m-grid';
    const detailToggleSelector = '[data-m-grid-detail-toggle]';
    const toggleAllSelector = '[data-m-grid-details-toggle-all]';

    const getGrid = (element) => element?.closest?.(gridSelector) ?? null;

    const getControlledDetailsRow = (button) => {
        const grid = getGrid(button);
        const detailRowId = button?.getAttribute('aria-controls');

        if (!grid || !detailRowId) {
            return null;
        }

        return grid.querySelector(`#${CSS.escape(detailRowId)}`);
    };

    const setRowDetailsState = (button, open) => {
        const detailRow = getControlledDetailsRow(button);
        const dataRow = button?.closest?.('[data-m-grid-data-row]');

        if (!detailRow) {
            return;
        }

        detailRow.hidden = !open;
        button.setAttribute('aria-expanded', String(open));
        button.setAttribute('aria-label', open ? 'Hide row details' : 'Show row details');
        button.textContent = open ? '-' : '+';
        dataRow?.classList.toggle('is-details-open', open);
    };

    const getDetailToggleButtons = (grid) => [...grid.querySelectorAll(detailToggleSelector)];

    const areAllDetailsOpen = (grid) => {
        const buttons = getDetailToggleButtons(grid);

        return buttons.length > 0 && buttons.every((button) => {
            const detailRow = getControlledDetailsRow(button);
            return detailRow && !detailRow.hidden;
        });
    };

    const syncGridDetailsToggle = (grid) => {
        const toggleAllButton = grid.querySelector(toggleAllSelector);

        if (!toggleAllButton) {
            return;
        }

        const allOpen = areAllDetailsOpen(grid);
        const label = allOpen ? 'Collapse all row details' : 'Expand all row details';
        toggleAllButton.textContent = allOpen ? '-' : '+';
        toggleAllButton.setAttribute('aria-label', label);
        toggleAllButton.setAttribute('title', label);
        toggleAllButton.setAttribute('aria-expanded', String(allOpen));
        toggleAllButton.dataset.mGridDetailsAction = allOpen ? 'collapse' : 'expand';
    };

    const setGridDetailsState = (grid, open) => {
        getDetailToggleButtons(grid).forEach((button) => {
            setRowDetailsState(button, open);
        });
        syncGridDetailsToggle(grid);
    };

    const normalizeGridDetails = (root = document) => {
        root.querySelectorAll(gridSelector).forEach((grid) => {
            getDetailToggleButtons(grid).forEach((button) => {
                const detailRow = getControlledDetailsRow(button);
                setRowDetailsState(button, detailRow ? !detailRow.hidden : false);
            });
            syncGridDetailsToggle(grid);
        });
    };

    document.addEventListener('click', (event) => {
        const target = event.target instanceof Element ? event.target : null;

        if (!target) {
            return;
        }

        const toggleButton = target.closest(detailToggleSelector);
        if (toggleButton) {
            event.preventDefault();
            setRowDetailsState(toggleButton, toggleButton.getAttribute('aria-expanded') !== 'true');
            const grid = getGrid(toggleButton);
            if (grid) {
                syncGridDetailsToggle(grid);
            }
            return;
        }

        const toggleAllButton = target.closest(toggleAllSelector);
        if (toggleAllButton) {
            event.preventDefault();
            const grid = getGrid(toggleAllButton);
            if (grid) {
                setGridDetailsState(grid, !areAllDetailsOpen(grid));
            }
        }
    });

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', () => normalizeGridDetails());
    } else {
        normalizeGridDetails();
    }

    document.body.addEventListener('htmx:afterSwap', (event) => {
        normalizeGridDetails(event.target);
    });
})();
