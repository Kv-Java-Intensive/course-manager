package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

  ModuleMapper MAPPER = Mappers.getMapper(ModuleMapper.class);

  Module moduleDtoToModule(ModuleDto moduleDto);

  ModuleDto moduleToModuleDto(Module module);
}
