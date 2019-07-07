package com.progex.eats.category;

import com.progex.eats.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author indunil
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private @NonNull String name;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Item> itemEntities = new ArrayList<>();

    public void addItem(Item item) {
        itemEntities.add(item);
        if (item.getCategory() != this){
            item.setCategory(this);
        }
    }
}
