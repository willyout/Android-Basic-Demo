package com.willyout.BasicDemo.service;

import com.willyout.BasicDemo.service.Person;
import com.willyout.BasicDemo.service.Pet;


interface IPet{
    List<Pet> getPets(in Person owner);
}