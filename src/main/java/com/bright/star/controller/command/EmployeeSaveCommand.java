package com.bright.star.controller.command;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author yanzou
 * @version V1.0.0
 * @date 2023/2/6
 * @description
 **/
@Schema(name = "社員save command", description = "社員save command")
public record EmployeeSaveCommand (@Schema Integer main){


}
