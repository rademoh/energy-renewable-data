package com.energyinvestmentdata.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationRequest implements Pageable {

    @Builder.Default
    @Min(value = 1, message = "Page must be greater than zero (0)")
    private Integer page = 1;

    @Builder.Default
    @Size(max = 100, message = "Page size must be greater than 0 but less than 100")
    private Integer size = 25;


    @Override
    public int getPageNumber() {
        return getPage();
    }

    @Override
    public int getPageSize() {
        return getSize();
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
