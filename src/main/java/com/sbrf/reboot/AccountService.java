package com.sbrf.reboot;

import com.sbrf.reboot.repository.AccountRepository;


public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean addNewContract(long clientId, long contractNumber) {
        if (this.isClientHasContract(clientId, contractNumber)) return false;
        else return accountRepository.addContractToClient(clientId, contractNumber);
    }
}
