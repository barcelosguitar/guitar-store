package br.com.barcelos_projects.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import br.com.barcelos_projects.model.Address;

@Stateful
public class AddressTemp {
    public static List<Address> address = new ArrayList<>();
}