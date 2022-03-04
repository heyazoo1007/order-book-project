package jpabook1.jpashop1.Controller;

import jpabook1.jpashop1.Service.ItemService;
import jpabook1.jpashop1.domain.item.Book;
import jpabook1.jpashop1.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book1 = new Book();
        book1.setAuthor(form.getAuthor());
        book1.setIsbn(form.getIsbn());
        book1.setStockQuantity(form.getStockQuantity());
        book1.setName(form.getName());
        book1.setPrice(form.getPrice());
        System.out.println(book1);
        itemService.saveItem(book1);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items= itemService.findAll();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model){
        Book one = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setAuthor(one.getAuthor());
        form.setName(one.getName());
        form.setPrice(one.getPrice());
        form.setIsbn(one.getIsbn());
        form.setStockQuantity(one.getStockQuantity());

        model.addAttribute("form",form);
        return "items/updateItemForm";

    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,@ModelAttribute("form")BookForm form) {

//    Book book = new Book();
//    book.setPrice(form.getPrice());
//    book.setName(form.getName());
//    book.setStockQuantity(form.getStockQuantity());
//    book.setIsbn(form.getIsbn());
//    book.setAuthor(form.getAuthor());
//    book.setId(form.getId());

    itemService.update(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
    return "redirect:/items";


    }
}
