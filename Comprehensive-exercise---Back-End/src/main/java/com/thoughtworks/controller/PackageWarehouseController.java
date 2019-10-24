package com.thoughtworks.controller;

import com.thoughtworks.core.PackageWarehouseModel;
import com.thoughtworks.service.PackageWarehouseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.NotSupportedException;

@RestController
@RequestMapping("/packageWarehouse")
public class PackageWarehouseController {
    @Autowired
    PackageWarehouseService packageWarehouseService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(produces = {"application/json"})
    public PackageWarehouseModel savePackage(@RequestBody PackageWarehouseModel packageWarehouseModel) throws NotSupportedException, NotFoundException {
        return packageWarehouseService.savePackage(packageWarehouseModel);
    }

    @GetMapping(produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<PackageWarehouseModel> getAllPackages(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
        return packageWarehouseService.getAllPackages(page, pageSize);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(consumes = "application/json", produces = "application/json")
    public PackageWarehouseModel setBookAPickUp(@RequestBody PackageWarehouseModel packageWarehouseModel) throws NotFoundException, NotSupportedException {
        return packageWarehouseService.setBookAPickUp(packageWarehouseModel);
    }
}
