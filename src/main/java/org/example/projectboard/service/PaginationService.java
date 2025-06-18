package org.example.projectboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_SIZE = 5;

    public List<Integer> getPaginationBarNumbers(int currentPage, int totalPages) {
        int startNumber = Math.max(0, currentPage - (BAR_SIZE / 2));
        int endNumber = Math.min(totalPages, startNumber + BAR_SIZE);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int currentBarLength() {
        return BAR_SIZE;
    }
}
