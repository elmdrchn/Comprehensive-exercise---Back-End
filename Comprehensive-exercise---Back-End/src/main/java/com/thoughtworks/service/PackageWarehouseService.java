package com.thoughtworks.service;

import com.thoughtworks.core.PackageWarehouseModel;
import com.thoughtworks.core.RecipientModel;
import com.thoughtworks.repository.PackageWarehouseRepository;
import com.thoughtworks.repository.RecipientRepository;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class PackageWarehouseService {
    private static final String PLEASE_INPUT_ALL_REQUIRED_FIELDS = "PLEASE INPUT ALL REQUIRED FIELDS";
    private static final String OBJECT_NOT_FOUND = "OBJECT NOT FOUND";
    private static final String NO_PICKUP = "No Pickup";
    private static final String SETTING_A_PICK_UP_MUST_BE_ONLY_APPLICABLE_FOR_NO_PICKUP_STATUS = "SETTING A PICK UP MUST BE ONLY APPLICABLE FOR NO PICKUP STATUS";

    @Autowired
    private PackageWarehouseRepository packageWarehouseRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    public PackageWarehouseModel savePackage(PackageWarehouseModel packageWarehouseModel) throws NotSupportedException {
        if(!packageWarehouseModel.getReceiverFullName().isEmpty()
            && packageWarehouseModel.getPhone() != null
            && !packageWarehouseModel.getWeight().isEmpty()){
            RecipientModel recipientModel = new RecipientModel();
            packageWarehouseModel.setStatus("No Pickup");
            recipientModel.setReceiverFullName(packageWarehouseModel.getReceiverFullName());
            RecipientModel save = recipientRepository.save(recipientModel);
            packageWarehouseModel.setReceiverID(save.getId());
            return packageWarehouseRepository.save(packageWarehouseModel);
        }
        throw new NotSupportedException(PLEASE_INPUT_ALL_REQUIRED_FIELDS);
    }

    public String getCurrentDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    public Iterable<PackageWarehouseModel> getAllPackages(Integer page, Integer pageSize) {
        return packageWarehouseRepository.findAll(PageRequest.of(page, pageSize));
    }

    public PackageWarehouseModel setBookAPickUp(PackageWarehouseModel packageWarehouseModel) throws NotFoundException, NotSupportedException {
        PackageWarehouseModel checkWayBillNumber = packageWarehouseRepository.findOneByWayBillNumber(packageWarehouseModel.getWayBillNumber());

        if(!isNull(checkWayBillNumber)){
            if(!packageWarehouseModel.getPickupTime().isEmpty()){
                if(checkWayBillNumber.getStatus() == NO_PICKUP){
                    checkWayBillNumber.setStatus("Reservation");
                    checkWayBillNumber.setPickupTime(packageWarehouseModel.getPickupTime());
                    return packageWarehouseRepository.save(checkWayBillNumber);
                }
                throw new NotSupportedException(SETTING_A_PICK_UP_MUST_BE_ONLY_APPLICABLE_FOR_NO_PICKUP_STATUS);
            }
            throw new NotSupportedException(PLEASE_INPUT_ALL_REQUIRED_FIELDS);
        }
        throw new NotFoundException(OBJECT_NOT_FOUND);
    }
}
