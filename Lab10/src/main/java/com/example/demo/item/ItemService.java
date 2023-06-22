package com.example.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {

    // Pobiera repozytorium pozwalającoe na działania na bazy danych
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Pobiera z bazy danych listę książek
    @Autowired
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public Item getById(Long id){
        return itemRepository.getOne(id);
    }

    public void createItem(Item newItem){
        itemRepository.save(newItem);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

    public void toggleTaken(Long id){
        Item item = itemRepository.getOne(id);
        item.setTaken(!item.isTaken());
        itemRepository.save(item);
    }

    public List<Item> getAvaliableItems(boolean isTaken){
        List<Item> items = new ArrayList<>();
        for (Item item: itemRepository.findAll()) {
            if(isTaken){
                if(item.isTaken()){
                    items.add(item);
                }
            }
            if(!isTaken){
                if(!item.isTaken()){
                    items.add(item);
                }
            }
        }
        return items;
    }
}
