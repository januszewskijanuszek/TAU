package test.controller;

import com.example.demo.item.Item;
import com.example.demo.item.ItemController;
import com.example.demo.item.ItemRepository;
import com.example.demo.item.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepository;
    private Model model;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    public void testAvBooks(){
        // wywołujemy avaliable books
        String name = itemController.avBooks(model);

        // Sprawdzanie czy poprawny  model został dobrany
        List<Item> avBooks = itemService.getAvaliableItems(false);
        assertEquals(avBooks, model.getAttribute("avBooks"));
        // Porównanie czy dobrą pobrało
        assertEquals("avBooks", name);
    }

    @Test
    public void testUnBooks(){
        String name = itemController.unBooks(model);

        List<Item> unBooks = itemService.getAvaliableItems(true);
        assertEquals(unBooks, model.getAttribute("avBooks"));

        assertEquals("unBooks", name);
    }

    @Test
    public void testAddBooksGet(){
        String name = itemController.addBooks(model);

        Item item = (Item) model.getAttribute("item");
        assertEquals(new Item(), item);

        assertEquals("addBooks", name);
    }

    @Test
    public void testAddBooksPost(){
        Item item = createItem();

        String name = itemController.submitItem(item, model);

        // Weryfikacja
        assertEquals("Jas i malgosia", model.getAttribute("name"));
        assertEquals("Nel", model.getAttribute("author"));
        assertEquals(false, model.getAttribute("taken"));
        assertEquals(new Date(), model.getAttribute("release"));
        assertEquals((long) 9999, model.getAttribute("id"));

        // Weryfikacja dodanie do bazy danych
        List<Item> avBooks = itemService.getAvaliableItems(false);
        assertEquals(1, avBooks.size());
        assertEquals(item, avBooks.get(0));

        assertEquals("redirect:/avBooks", name);
    }
     @Test
    public void testDeleteBook(){
        Item item = createItem();

        itemService.createItem(item);
        List<Item> avBooksBeforeDeletion = itemService.getAvaliableItems(false);

        String name = itemController.deleteBook(model, item.getId());

        // Weryfikacja czy usunięto
         List<Item> avBooksAfterDeletetion = itemService.getAvaliableItems(false);
         assertEquals(avBooksBeforeDeletion.size() - 1, avBooksAfterDeletetion.size());
         assertEquals(item, itemService.getById(item.getId()));
         assertEquals("redirect:/avBooks", name);
     }
     private Item createItem(){
        Item item = new Item();
        item.setName("Jas i malgosia");
        item.setAuthor("Nel");
        item.setTaken(false);
        item.setRelease(new Date());
        item.setId((long) 9999);
        return item;
    }
}
