package dev.alphacodez.marketplace.store;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("api/v1/store")
@RestController
public class StoreController {

    private final StoreService service;

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getAllStores() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok(service.getAllStores());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStore(@ModelAttribute StoreDto request) throws Exception {
        service.createStore(request);
        return ResponseEntity.ok("store created successfully");
    }


}
