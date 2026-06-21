package com.demo.baseproject;

import java.util.List;

public class GridView {

    private final String id;
    private final String instanceId;
    private final String domId;
    private final String baseUrl;
    private final String title;
    private final String loadedText;
    private final int page;
    private final int previousPage;
    private final int nextPage;
    private final int totalPages;
    private final int pageSize;
    private final List<Integer> pageSizeOptions;
    private final List<Integer> pageNumbers;
    private final String currentSortQuery;
    private final List<Column> visibleColumns;
    private final List<Row> rows;
    private final String actionsLabel;
    private final String emptyText;

    public GridView(
            String id,
            String instanceId,
            String domId,
            String baseUrl,
            String title,
            String loadedText,
            int page,
            int previousPage,
            int nextPage,
            int totalPages,
            int pageSize,
            List<Integer> pageSizeOptions,
            List<Integer> pageNumbers,
            String currentSortQuery,
            List<Column> visibleColumns,
            List<Row> rows,
            String actionsLabel,
            String emptyText
    ) {
        this.id = id;
        this.instanceId = instanceId;
        this.domId = domId;
        this.baseUrl = baseUrl;
        this.title = title;
        this.loadedText = loadedText;
        this.page = page;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.pageSizeOptions = pageSizeOptions;
        this.pageNumbers = pageNumbers;
        this.currentSortQuery = currentSortQuery;
        this.visibleColumns = visibleColumns;
        this.rows = rows;
        this.actionsLabel = actionsLabel;
        this.emptyText = emptyText;
    }

    public String getId() {
        return id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getDomId() {
        return domId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLoadedText() {
        return loadedText;
    }

    public int getPage() {
        return page;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Integer> getPageSizeOptions() {
        return pageSizeOptions;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public String getCurrentSortQuery() {
        return currentSortQuery;
    }

    public List<Column> getVisibleColumns() {
        return visibleColumns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public String getActionsLabel() {
        return actionsLabel;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

    public boolean isHasDetailFields() {
        return rows.stream().anyMatch(row -> !row.getDetailCells().isEmpty());
    }

    public boolean isHasRowActions() {
        return rows.stream().anyMatch(row -> !row.getActions().isEmpty());
    }

    public boolean isHasTotals() {
        return false;
    }

    public boolean isHasPrevious() {
        return page > 1;
    }

    public boolean isHasNext() {
        return page < totalPages;
    }

    public boolean isHasPagination() {
        return totalPages > 0;
    }

    public boolean isExportPdfUrl() {
        return false;
    }

    public boolean isExportXlsxUrl() {
        return false;
    }

    public List<Cell> getTotalCells() {
        return List.of();
    }

    public static class Column {
        private final String label;
        private final String alignCss;
        private final String widthStyle;
        private final boolean sortable;
        private final String nextSortQuery;

        public Column(String label, String alignCss, String widthStyle) {
            this.label = label;
            this.alignCss = alignCss;
            this.widthStyle = widthStyle;
            this.sortable = false;
            this.nextSortQuery = "";
        }

        public String getLabel() {
            return label;
        }

        public String getAlignCss() {
            return alignCss;
        }

        public String getWidthStyle() {
            return widthStyle;
        }

        public boolean isSortable() {
            return sortable;
        }

        public String getNextSortQuery() {
            return nextSortQuery;
        }
    }

    public static class Row {
        private final String safeKey;
        private final String cssClass;
        private final List<Cell> visibleCells;
        private final List<Cell> detailCells;
        private final List<Action> actions;

        public Row(String safeKey, List<Cell> visibleCells, List<Cell> detailCells, List<Action> actions) {
            this.safeKey = safeKey;
            this.cssClass = "";
            this.visibleCells = visibleCells;
            this.detailCells = detailCells;
            this.actions = actions;
        }

        public String getSafeKey() {
            return safeKey;
        }

        public String getCssClass() {
            return cssClass;
        }

        public List<Cell> getVisibleCells() {
            return visibleCells;
        }

        public List<Cell> getDetailCells() {
            return detailCells;
        }

        public List<Action> getActions() {
            return actions;
        }
    }

    public static class Cell {
        private final String label;
        private final String value;
        private final String cssClass;

        public Cell(String label, String value, String cssClass) {
            this.label = label;
            this.value = value;
            this.cssClass = cssClass;
        }

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }

        public String getCssClass() {
            return cssClass;
        }
    }

    public static class Action {
        private final String label;
        private final String url;
        private final String method;
        private final String cssClass;

        public Action(String label, String url, String method, String cssClass) {
            this.label = label;
            this.url = url;
            this.method = method;
            this.cssClass = cssClass;
        }

        public String getLabel() {
            return label;
        }

        public String getUrl() {
            return url;
        }

        public String getMethod() {
            return method;
        }

        public String getCssClass() {
            return cssClass;
        }

        public boolean isPost() {
            return "post".equalsIgnoreCase(method);
        }
    }
}
