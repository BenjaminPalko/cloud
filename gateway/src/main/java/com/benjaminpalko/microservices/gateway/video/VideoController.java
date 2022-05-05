package com.benjaminpalko.microservices.gateway.video;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(path = "/video")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Map> getVideoFiles() {
        return videoService
                .getAllVideos()
                .map(gridFSFile -> Map.of(
                        "id", gridFSFile.getObjectId().toHexString(),
                        "filename", gridFSFile.getFilename(),
                        "uploadDate", gridFSFile.getUploadDate()
                ));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> uploadVideos(@RequestPart("videos")Flux<FilePart> fileParts) {
        return fileParts
                .log()
                .filter(filePart -> filePart.filename().matches(".*(\\.mp4)"))
                .flatMap(filePart -> videoService.saveVideo(filePart.content(), filePart.filename()))
                .map(ObjectId::toHexString);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map> getVideo(@PathVariable String id) {
        return videoService
                .getVideo(id)
                .map(gridFSFile -> Map.of(
                        "id", gridFSFile.getObjectId().toHexString(),
                        "filename", gridFSFile.getFilename(),
                        "uploadDate", gridFSFile.getUploadDate()
                ));
    }

    @RequestMapping(path = "/stream/{id}", method = RequestMethod.GET, produces = "video/mp4")
    public Flux<DataBuffer> streamVideo(@PathVariable String id) {
        return videoService
                .getVideoResource(id)
                .flatMapMany(ReactiveGridFsResource::getDownloadStream);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteVideo(@PathVariable String id) {
        return videoService.deleteVideo(id);
    }
}
