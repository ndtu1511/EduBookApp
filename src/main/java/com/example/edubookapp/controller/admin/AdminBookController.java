package com.example.edubookapp.controller.admin;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Category;
import com.example.edubookapp.service.AuthorService;
import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.CategoryService;
import com.example.edubookapp.service.PublisherService;
import com.example.edubookapp.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Controller
public class AdminBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookValidator bookValidator;
    public static String urlAdd=null;
    @GetMapping("/admin/book")
    public String index(Model model){
        model.addAttribute("books",bookService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/book_list";
    }
    @GetMapping("/book/{id}")
    public String show(@PathVariable("id") Integer id,Model model){
        model.addAttribute("book", bookService.findOne(id).get());
        model.addAttribute("categories",categoryService.findAll());
        return "book_detail";
    }
    @GetMapping("/admin/book/add")
    public String add(Model model, HttpServletRequest request){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.findAll());
        urlAdd=request.getRequestURI();
        return "admin/book_form";
    }
    @GetMapping("/admin/book/{id}/edit")
    public String edit(@PathVariable("id") Integer id,Model model, HttpServletRequest request){
        model.addAttribute("book",bookService.findOneWithCategory(id));
        model.addAttribute("categories",categoryService.findAll());
        urlAdd = request.getRequestURI();
        return "admin/book_form";
    }
    @GetMapping("/admin/book/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("success","Delete successfully");
        return "redirect:/admin/book";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Optional<Category> category = categoryService.findOne(Integer.parseInt(text));
                setValue(category.get());
            }
        });
    }
    @PostMapping("/admin/book/save")
    public String save(@Valid Book book, BindingResult result, Model model, RedirectAttributes redirect){
        bookValidator.validate(book,result);
        model.addAttribute("categories",categoryService.findAll());
        if (result.hasErrors()){
            return "admin/book_form";
        }
        bookService.register(book);
        redirect.addFlashAttribute("success","Saved this book successfully");
        return "redirect:/admin/book";
    }
    @GetMapping("/admin/book/{id}/upload")
    public String getUpload(@PathVariable("id") Integer id,Model model){
        model.addAttribute("book",bookService.findOne(id).get());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/book_upload";
    }
    @PostMapping("/admin/book/{id}/upload")
    public String postUpload(@PathVariable("id") Integer id,
                             @RequestParam("imageLink")MultipartFile imageFile,
                             RedirectAttributes redirectAttributes){
        Book book = bookService.findOne(id).get();
        bookService.upload(book,imageFile);

        redirectAttributes.addFlashAttribute("success","Upload image successfully");
        return "redirect:/book/{id}";
    }
}
