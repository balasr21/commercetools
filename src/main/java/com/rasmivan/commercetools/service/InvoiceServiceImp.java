package com.rasmivan.commercetools.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasmivan.commercetools.dto.InvoiceDto;
import com.rasmivan.commercetools.exception.DatabaseException;
import com.rasmivan.commercetools.exception.ErroneousJsonException;
import com.rasmivan.commercetools.exception.InvalidProduct;
import com.rasmivan.commercetools.repository.InvoiceRepository;
import com.rasmivan.commercetools.repository.ProductRepository;
import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.domain.Invoice;

/**
 * The Class InvoiceServiceImp.
 */
@Service
public class InvoiceServiceImp implements InvoiceService {
	
	/** The product repository. */
	@Autowired
	ProductRepository productRepository;
	
	/** The invoice repository. */
	@Autowired
	InvoiceRepository invoiceRepository;
	
	/** The product service. */
	@Autowired
	ProductService productService;
	
	/**
	 * add invoice .
	 *
	 * @param invoiceDto the invoice dto
	 * @return the invoice
	 */
	@Override
	public Invoice addInvoice(InvoiceDto invoiceDto) {
		if(checkForNullEmptyInvoiceDto(invoiceDto)) {
			try {
				return invoiceRepository.save(copyInvoiceProperties(invoiceDto)); // JPA Save
			} catch(Exception e) {
				throw new DatabaseException(MessageConstantsUtils.ERROR_SAVING + e.getMessage());
			}
		} else {
			throw new InvalidProduct(MessageConstantsUtils.INVALID_PRODUCT);
		}
	}
	
	/**
	 * Copy invoice properties.
	 *
	 * @param invoiceDto the invoice dto
	 * @return the invoice
	 */
	private Invoice copyInvoiceProperties(InvoiceDto invoiceDto) {
		if(!productService.productExists(invoiceDto.getProductId())) {
			throw new ErroneousJsonException(MessageConstantsUtils.PRODUCT_NOTFOUND);
		}
		Invoice inv = new Invoice();
		if(invoiceDto.getId() != null) {
			Optional<Invoice> optInv = invoiceRepository.findById(invoiceDto.getId());
			if(optInv.isPresent()) {
				inv = optInv.get();
			} else {
				throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_ID);
			}
		}
		inv.setTimestamp(invoiceDto.getTimestamp());
		inv.setQuantity(invoiceDto.getQuantity());
		inv.setProductId(productRepository.findByProductId(invoiceDto.getProductId()));
		return inv;
	}

	/**
	 * Check for null empty invoice dto.
	 *
	 * @param invoiceDto the invoice dto
	 * @return true, if successful
	 */
	private boolean checkForNullEmptyInvoiceDto(InvoiceDto invoiceDto) {
		return invoiceDto != null && invoiceDto.getProductId() != null && invoiceDto.getQuantity() != null && invoiceDto.getTimestamp() != null;
	}
	
}
