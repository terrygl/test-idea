package test.joe.spring.springapp.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ReferenceServiceTest.class, CustomerServiceTest.class, AccountServiceTest.class, AddressServiceTest.class })
public class ServiceTestSuite {

}
