package com.example.edubookapp.controller.admin;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Category;
import com.example.edubookapp.service.*;
import com.example.edubookapp.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private PendingBookService pendingBookService;
    @Autowired
    private PendingCommentService pendingCommentService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BookValidator bookValidator;
    @GetMapping("/admin/book")
    public String index(Model model){
        model.addAttribute("books",bookService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/book_list";
    }

    @GetMapping("/admin/pendingBook")
    public String indexPending(Model model) {
        model.addAttribute("books", pendingBookService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/pending_book_list";
    }

    @GetMapping("/admin/pendingComment")
    public String showPendingComment(Model model) {
        model.addAttribute("pendingComments", pendingCommentService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/pending_comment_list";
    }

    @GetMapping("/admin/pendingComment/{id}/save")
    public String savePendingComment(@PathVariable Integer id, RedirectAttributes redirect) {
        commentService.register(pendingCommentService.findOne(id).get());
        pendingCommentService.delete(id);
        redirect.addFlashAttribute("success", "Save comment successfully");
        return "redirect:/admin/pendingComment";
    }

    @GetMapping("/admin/pendingComment/{id}/delete")
    public String deletePendingComment(@PathVariable Integer id, RedirectAttributes redirect) {
        pendingCommentService.delete(id);
        redirect.addFlashAttribute("success", "Delete comment successfully");
        return "redirect:/admin/pendingComment";
    }
    @GetMapping("/admin/book/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book",bookService.findOneWithCategory(id));
        model.addAttribute("categories",categoryService.findAll());
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

    @PostMapping("/admin/book/{requestUsername}/{id}/register")
    public String registerNewBook(@PathVariable Integer id,
                                  @PathVariable String requestUsername,
                                  RedirectAttributes redirect) {
        bookService.registerPending(pendingBookService.findByIdAndRequestUsername(id, requestUsername));
        pendingBookService.delete(id);
        redirect.addFlashAttribute("success", "Post this book successfully");
        return "redirect:/admin/book";
    }

    @GetMapping("/admin/book/{requestUsername}/{id}/delete")
    public String deletePendingBook(@PathVariable Integer id,
                                    @PathVariable String requestUsername,
                                    RedirectAttributes redirect) {
        pendingBookService.delete(id);
        redirect.addFlashAttribute("success", "Delete this book successfully");
        return "redirect:/admin/book";
    }
    @GetMapping("/admin/book/{id}/upload")
    public String getUpload(@PathVariable("id") Integer id,Model model){
        model.addAttribute("book",bookService.findOne(id).get());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/book_upload";
    }

    @PostMapping("/admin/book/{id}/upload/image")
    public String postUploadImage(@PathVariable("id") Integer id,
                             @RequestParam("imageLink")MultipartFile imageFile,
                             RedirectAttributes redirectAttributes){
        Book book = bookService.findOne(id).get();
        bookService.uploadImage(book, imageFile);

        redirectAttributes.addFlashAttribute("success", "Upload image successfully");
        return "redirect:/book/{id}";
    }

    @PostMapping("/admin/book/{id}/upload/content")
    public String postUploadContent(@PathVariable("id") Integer id,
                                    @RequestParam("contentLink") MultipartFile contentFile,
                                    RedirectAttributes redirectAttributes) {
        Book book = bookService.findOne(id).get();
        bookService.uploadContent(book, contentFile);

        redirectAttributes.addFlashAttribute("success","Upload image successfully");
        return "redirect:/book/{id}";
    }
}
