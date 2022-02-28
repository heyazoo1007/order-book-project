package jpabook1.jpashop1.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@DiscriminatorValue("A")
@Entity
public class Album extends Item{

    private String artist;
    private String etc;

}
