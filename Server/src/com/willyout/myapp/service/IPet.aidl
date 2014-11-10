package com.willyout.myapp.service;

import com.willyout.myapp.service.Person;
import com.willyout.myapp.service.Pet;


interface IPet{
    List<Pet> getPets(in Person owner);
}