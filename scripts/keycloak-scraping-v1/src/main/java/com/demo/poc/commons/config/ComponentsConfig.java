package com.demo.poc.commons.config;

import com.demo.poc.commons.service.OperationSelectorService;
import com.demo.poc.commons.service.OperationService;
import com.demo.poc.entrypoint.client.service.ClientCreationService;
import com.demo.poc.entrypoint.client.spider.ClientSpider;
import com.demo.poc.entrypoint.login.service.LoginService;
import com.demo.poc.entrypoint.login.spider.LoginSpider;
import com.demo.poc.entrypoint.realm.service.RealmCreationService;
import com.demo.poc.entrypoint.realm.spider.RealmConfigSpider;
import com.demo.poc.entrypoint.realm.spider.RealmCreatorSpider;
import com.demo.poc.entrypoint.role.service.RoleCreationService;
import com.demo.poc.entrypoint.role.spider.RoleSpider;
import com.demo.poc.entrypoint.user.service.AssignmentUserRoleService;
import com.demo.poc.entrypoint.user.service.UserCreationService;
import com.demo.poc.entrypoint.user.spider.AssignmentUserRoleSpider;
import com.demo.poc.entrypoint.user.spider.UserSpider;
import com.google.inject.AbstractModule;
import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.helper.DriverHelper;
import com.google.inject.multibindings.Multibinder;

public class ComponentsConfig extends AbstractModule {

  @Override
  protected void configure() {
    //config
    bind(PropertiesReader.class).toInstance(new PropertiesReader());
    bind(DriverHelper.class);
    Multibinder<OperationService> binderSet = Multibinder.newSetBinder(binder(), OperationService.class);

    //commons
    bind(OperationSelectorService.class);

    //login
    binderSet.addBinding().to(LoginService.class);
    bind(LoginSpider.class);

    //realm
    binderSet.addBinding().to(RealmCreationService.class);
    bind(RealmConfigSpider.class);
    bind(RealmCreatorSpider.class);

    //user
    binderSet.addBinding().to(UserCreationService.class);
    bind(UserSpider.class);

    //roles
    binderSet.addBinding().to(RoleCreationService.class);
    bind(RoleSpider.class);

    //assignment roles
    binderSet.addBinding().to(AssignmentUserRoleService.class);
    bind(AssignmentUserRoleSpider.class);

    //client
    binderSet.addBinding().to(ClientCreationService.class);
    bind(ClientSpider.class);
  }
}
