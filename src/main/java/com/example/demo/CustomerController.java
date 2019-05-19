package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/init")
    public String bulkcreate(){
// save a single Customer
        repository.save(new Customer("Ivan", "Petrov"));
// save a list of Customers
        repository.saveAll(Arrays.asList(new Customer("Egor", "Sviridov")
                , new Customer("Danil", "Bobrov")
                , new Customer("Albert", "Germanich")
                , new Customer("Petr", "Petrov")));
        return "Customers are created";
    }

    @PostMapping("/create")
    public String create(@RequestBody CustomerUI customer){
// save a single Customer
        repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
        return "Customer is created";
    }
    @GetMapping("/findall")
    public List<CustomerUI> findAll(){
        List<Customer> customers = repository.findAll();
        List<CustomerUI> customerUI = new ArrayList<>();
        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getId(), customer.getFirstName(),customer.getLastName()));
        }
        return customerUI;
    }
    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = repository.findById(id).toString();
        return customer;
    }
    @RequestMapping("/searchbyfirstname/{firstname}")
    public List<CustomerUI> fetchDataByFirstName(@PathVariable String firstname){
        List<Customer> customers = repository.findByFirstName(firstname);
        List<CustomerUI> customerUI = new ArrayList<>();
        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }
        return customerUI;
    }
}
