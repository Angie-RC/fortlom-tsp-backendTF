package com.example.fortlomtsp.backend.api;


import com.example.fortlomtsp.backend.domain.service.FanaticService;
import com.example.fortlomtsp.backend.mapping.FanaticMapper;
import com.example.fortlomtsp.backend.resource.fanatic.CreateFanaticResource;
import com.example.fortlomtsp.backend.resource.fanatic.FanaticResource;
import com.example.fortlomtsp.backend.resource.fanatic.UpdateFanaticResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/userservice/fanatics")
public class FanaticController {
    @Autowired
    private FanaticService fanaticService;

    @Autowired
    private FanaticMapper mapper;

    @GetMapping
    public Page<FanaticResource> getAllFanatics(Pageable pageable) {
        return mapper.modelListToPage(fanaticService.getAll(), pageable);
    }
    @GetMapping("{fanaticId}")
    public FanaticResource getUserById(@PathVariable("fanaticId") Long fanaticId) {
        return mapper.toResource(fanaticService.getById(fanaticId));
    }
    @GetMapping("/username/{fanaticname}")
    public FanaticResource getUserByfanaticname(@PathVariable("fanaticname") String fanaticname) {
        return mapper.toResource(fanaticService.getbyUsername(fanaticname));
    }
    @GetMapping("/name/{fanaitcname}/lastname/{fanaticlastname}")
    public FanaticResource getUserByartistnameandlastname(@PathVariable("fanaitcname") String fanaitcname, @PathVariable("fanaticlastname") String fanaticlastname) {
        return mapper.toResource(fanaticService.getbyNameandLastname(fanaitcname,fanaticlastname));
    }
    @PostMapping
    public FanaticResource createUser(@RequestBody CreateFanaticResource request) {

        return mapper.toResource(fanaticService.create(mapper.toModel(request)));
    }
    @PutMapping("{fanaticId}")
    public FanaticResource updateUser(@PathVariable Long fanaticId, @RequestBody UpdateFanaticResource request) {
        return mapper.toResource(fanaticService.update(fanaticId, mapper.toModel(request)));
    }
    @DeleteMapping("{fanaticId}")
    public ResponseEntity<?> deletePost(@PathVariable Long fanaticId) {
        return fanaticService.delete(fanaticId);
    }
    @GetMapping("/check/{fanaticId}")
    public boolean existsartistid(@PathVariable("fanaticId") Long fanaticId){
        return fanaticService.existsfanatic(fanaticId);
    }
    @PutMapping("/ban/{fanaticId}")
    public FanaticResource BanArtist(@PathVariable("fanaticId") Long fanaticId){
        return mapper.toResource(fanaticService.banFanatic(fanaticId));
    }
}
