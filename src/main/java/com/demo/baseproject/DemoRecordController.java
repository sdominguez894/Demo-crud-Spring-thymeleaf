package com.demo.baseproject;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class DemoRecordController {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final List<Integer> PAGE_SIZE_OPTIONS = IntStream.rangeClosed(1, 10)
            .map(value -> value * 10)
            .boxed()
            .toList();

    private final DemoRecordRepository repository;

    public DemoRecordController(DemoRecordRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", "/records"})
    public String list(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model
    ) {
        String trimmedSearch = search.trim();
        int sanitizedPageSize = sanitizePageSize(pageSize);
        model.addAttribute("search", trimmedSearch);
        List<DemoRecord> records = trimmedSearch.isEmpty()
                ? repository.findAllByOrderByIdAsc()
                : repository.findByNameContainingIgnoreCaseOrderByIdAsc(trimmedSearch);
        model.addAttribute("records", records);
        model.addAttribute("grid", recordsGrid(records, page, sanitizedPageSize));
        return view("pages/list", "Registers", hxRequest, model);
    }

    @GetMapping("/records/new")
    public String create(
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model
    ) {
        DemoRecord record = new DemoRecord();
        record.setActive(true);
        record.setCreationDate(LocalDate.now());
        model.addAttribute("record", record);
        model.addAttribute("pageTitle", "Add register");
        return view("pages/form", "Add register", hxRequest, model);
    }

    @GetMapping("/records/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model
    ) {
        DemoRecord record = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid record id: " + id));
        model.addAttribute("record", record);
        model.addAttribute("pageTitle", "Modify register");
        return view("pages/form", "Modify register", hxRequest, model);
    }

    @PostMapping("/records")
    public String save(
            @Valid @ModelAttribute("record") DemoRecord record,
            BindingResult bindingResult,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (record.getCreationDate() == null) {
            record.setCreationDate(LocalDate.now());
        }

        if (bindingResult.hasErrors()) {
            String pageTitle = record.getId() == null ? "Add register" : "Modify register";
            model.addAttribute("pageTitle", pageTitle);
            return view("pages/form", pageTitle, hxRequest, model);
        }

        repository.save(record);
        redirectAttributes.addFlashAttribute("message", "Register saved successfully.");
        return "redirect:/records";
    }

    @PostMapping("/records/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Register deleted successfully.");
        return "redirect:/records";
    }

    private String view(String pageTemplate, String pageTitle, String hxRequest, Model model) {
        if ("true".equals(hxRequest)) {
            return pageTemplate + " :: pageContent";
        }

        model.addAttribute("activeItem", "records");
        model.addAttribute("pageTemplate", pageTemplate);
        model.addAttribute("pageTitle", pageTitle);
        return "layout";
    }

    private GridView recordsGrid(List<DemoRecord> records, int requestedPage, int pageSize) {
        List<GridView.Column> columns = List.of(
                new GridView.Column("ID", "", "width: 72px"),
                new GridView.Column("Name", "", ""),
                new GridView.Column("Active", "", "width: 110px"),
                new GridView.Column("Creation date", "", "width: 150px")
        );

        int totalRecords = records.size();
        int totalPages = totalRecords == 0 ? 1 : (int) Math.ceil((double) totalRecords / pageSize);
        int page = sanitizePage(requestedPage, totalPages);
        int startIndex = Math.min((page - 1) * pageSize, totalRecords);
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        List<GridView.Row> rows = records.subList(startIndex, endIndex).stream()
                .map(record -> new GridView.Row(
                        String.valueOf(record.getId()),
                        List.of(
                                new GridView.Cell("ID", String.valueOf(record.getId()), ""),
                                new GridView.Cell("Name", record.getName(), ""),
                                new GridView.Cell("Active", record.isActive() ? "Yes" : "No", record.isActive() ? "is-active" : "is-inactive"),
                                new GridView.Cell("Creation date", formatDate(record), "")
                        ),
                        List.of(
                                new GridView.Cell("Name", record.getName(), ""),
                                new GridView.Cell("Active", record.isActive() ? "Yes" : "No", ""),
                                new GridView.Cell("Creation date", formatDate(record), "")
                        ),
                        List.of(
                                new GridView.Action("Edit", "/records/%d/edit".formatted(record.getId()), "get", "is-link is-light"),
                                new GridView.Action("Delete", "/records/%d/delete".formatted(record.getId()), "post", "is-danger")
                        )
                ))
                .toList();

        String loadedText = records.isEmpty()
                ? "No registers found"
                : "Registers %d to %d of %d".formatted(startIndex + 1, endIndex, totalRecords);

        return new GridView(
                "records",
                "records-grid",
                "records-grid",
                "/records",
                "Registers",
                loadedText,
                page,
                Math.max(page - 1, 1),
                Math.min(page + 1, totalPages),
                totalPages,
                pageSize,
                PAGE_SIZE_OPTIONS,
                IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .toList(),
                "",
                columns,
                rows,
                "Actions",
                "No registers found."
        );
    }

    private int sanitizePage(int page, int totalPages) {
        if (page < 1) {
            return 1;
        }

        return Math.min(page, totalPages);
    }

    private int sanitizePageSize(int pageSize) {
        if (pageSize < 10) {
            return 10;
        }

        if (pageSize > 100) {
            return 100;
        }

        return (pageSize / 10) * 10;
    }

    private String formatDate(DemoRecord record) {
        return record.getCreationDate() == null ? "" : DATE_FORMATTER.format(record.getCreationDate());
    }
}
