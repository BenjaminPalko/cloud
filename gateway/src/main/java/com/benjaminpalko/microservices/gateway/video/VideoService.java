package com.benjaminpalko.microservices.gateway.video;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VideoService {

    private final ReactiveGridFsTemplate gridFsTemplate;

    @Autowired
    public VideoService(ReactiveGridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public Flux<GridFSFile> getAllVideos() {
        return gridFsTemplate.find(Query.query(Criteria.where("metadata._contentType").is("video")));
    }

    public Mono<GridFSFile> getVideo(String id) {
        return gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id).and("metadata._contentType").is("video")));
    }

    public Mono<ReactiveGridFsResource> getVideoResource(String id) {
        return gridFsTemplate
                .findOne(Query.query(Criteria.where("_id").is(id).and("metadata._contentType").is("video")))
                .flatMap(gridFsTemplate::getResource);
    }

    public Mono<ObjectId> saveVideo(Flux<DataBuffer> content, String filename) {
        return gridFsTemplate.store(content, filename, "video");
    }

    public Mono<Void> deleteVideo(String id) {
        return gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id).and("metadata._contentType").is("video")));
    }
}
