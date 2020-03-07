/*
 * Copyright (C) 2016-2020 the original author or authors. 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.viglet.shio.api.ecommerce;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import com.viglet.shio.api.ShJsonView;
import com.viglet.shio.persistence.model.ecommerce.ShEcomPaymentTypeDefinition;
import com.viglet.shio.persistence.repository.ecommerce.ShEcomPaymentTypeDefinitionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Alexandre Oliveira
 */
@RestController
@RequestMapping("/api/v2/ecom/payment/type/definition")
@Api(tags = "Payment Type Definition", description = "Payment Type Definition API")
public class ShEcomPaymentTypeDefinitionAPI {

	@Autowired
	private ShEcomPaymentTypeDefinitionRepository shEcomPaymentTypeDefinitionRepository;

	@ApiOperation(value = "Payment Type list")
	@GetMapping
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public List<ShEcomPaymentTypeDefinition> shEcomPaymentTypeDefinitionList() throws Exception {
		return shEcomPaymentTypeDefinitionRepository.findAll();
	}

	@ApiOperation(value = "Show a Payment Type")
	@GetMapping("/{id}")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinitioneGet(@PathVariable String id) throws Exception {
		return shEcomPaymentTypeDefinitionRepository.findById(id).orElse(null);
	}

	@ApiOperation(value = "Update a Payment Type")
	@PutMapping("/{id}")
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinitionUpdate(@PathVariable String id,
			@RequestBody ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinition) throws Exception {
		Optional<ShEcomPaymentTypeDefinition> shEcomPaymentTypeDefinitionOptional = shEcomPaymentTypeDefinitionRepository
				.findById(id);
		if (shEcomPaymentTypeDefinitionOptional.isPresent()) {
			ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinitionEdit = shEcomPaymentTypeDefinitionOptional.get();

			shEcomPaymentTypeDefinitionEdit.setDate(new Date());
			shEcomPaymentTypeDefinitionEdit.setClassName(shEcomPaymentTypeDefinition.getClassName());
			shEcomPaymentTypeDefinitionEdit.setDescription(shEcomPaymentTypeDefinition.getDescription());
			shEcomPaymentTypeDefinitionEdit.setName(shEcomPaymentTypeDefinition.getName());
			shEcomPaymentTypeDefinitionEdit.setSettingPath(shEcomPaymentTypeDefinition.getSettingPath());

			shEcomPaymentTypeDefinitionRepository.saveAndFlush(shEcomPaymentTypeDefinitionEdit);

			return shEcomPaymentTypeDefinitionEdit;
		} else {
			return null;
		}
	}

	@Transactional
	@ApiOperation(value = "Delete a Payment Type")
	@DeleteMapping("/{id}")
	public boolean shEcomPaymentTypeDefinitionDelete(@PathVariable String id) throws Exception {
		shEcomPaymentTypeDefinitionRepository.findById(id).ifPresent(new Consumer<ShEcomPaymentTypeDefinition>() {
			@Override
			public void accept(ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinition) {
				shEcomPaymentTypeDefinitionRepository.delete(shEcomPaymentTypeDefinition);
			}
		});
		return true;
	}

	@ApiOperation(value = "Create a Payment Type")
	@PostMapping
	@JsonView({ ShJsonView.ShJsonViewObject.class })
	public ShEcomPaymentTypeDefinition ShEcomPaymentTypeAdd(
			@RequestBody ShEcomPaymentTypeDefinition shEcomPaymentTypeDefinition) throws Exception {
		shEcomPaymentTypeDefinition.setDate(new Date());
		shEcomPaymentTypeDefinitionRepository.save(shEcomPaymentTypeDefinition);

		return shEcomPaymentTypeDefinition;

	}
}
