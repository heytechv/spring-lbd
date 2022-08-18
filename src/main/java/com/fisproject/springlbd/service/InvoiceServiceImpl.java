package com.fisproject.springlbd.service;

import com.fisproject.springlbd.entity.Employee;
import com.fisproject.springlbd.entity.Invoice;
import com.fisproject.springlbd.repository.EmployeeRepository;
import com.fisproject.springlbd.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl {

    private InvoiceRepository invoiceRepository;


    /** Private
     * */
    private Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }


    /** Public
     * */
    public Invoice getById(Long id) {
        return findById(id);
    }


}
