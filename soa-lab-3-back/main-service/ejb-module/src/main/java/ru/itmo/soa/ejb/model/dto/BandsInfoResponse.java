package ru.itmo.soa.ejb.model.dto;

import lombok.Data;
import ru.itmo.soa.ejb.model.Band;

import java.util.List;

@Data
public class BandsInfoResponse {
        private List<Band> data;
        private int total;
        private int totalPages;
        private int currentPage;
        private int size;
}
