package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ICustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

//    @RequestMapping(value = "/customers",method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>>list(){
        List<Customer> customers=customerService.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
        }
    }
//    @RequestMapping(value = "/customers/",method = RequestMethod.POST)
    @PostMapping("/")
    public ResponseEntity<Customer>create(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>byId(@PathVariable Long id){
        Customer customer= customerService.findById(id);
        if (customer!=null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Customer>(HttpStatus.OK);
        }
    }
//    @RequestMapping(value = "/customers/{id}",method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Customer>edit(@RequestBody Customer customer,@PathVariable Long id){
        Customer customers=customerService.findById(id);
        if (customer==null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }else {
            customers.setFirstName(customer.getFirstName());
            customers.setLastName(customer.getLastName());
            customerService.save(customers);
            return new ResponseEntity<Customer>(customers,HttpStatus.OK);
        }
    }
//    @RequestMapping(value = "/customers/{id}",method =RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer>delete(@PathVariable Long id){
        Customer customer= customerService.findById(id);
        if (customer==null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }else {
            customerService.delete(id);
            return new ResponseEntity<Customer>(customer,HttpStatus.NO_CONTENT);
        }

    }

}
