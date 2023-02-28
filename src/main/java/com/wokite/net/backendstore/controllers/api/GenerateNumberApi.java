package com.wokite.net.backendstore.controllers.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface GenerateNumberApi {

    @GetMapping(value = APP_ROOT + "/generated/generateCodeAppro")
    @ApiOperation(value = "Générer un code unique",
            notes = "Cette méthode permet de générer automatiquement un code unique")
    long generateCodeNumber();

}
