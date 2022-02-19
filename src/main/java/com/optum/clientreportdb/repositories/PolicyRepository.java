package com.optum.clientreportdb.repositories;

import com.optum.clientreportdb.model.PolicyModel;

public interface PolicyRepository {

    public int save(PolicyModel policyModel);
    public int update(PolicyModel policyModel);
    public PolicyModel findByPolicyNumber(String policyNumber);
    public int deleteById(int policyNumber);

}
