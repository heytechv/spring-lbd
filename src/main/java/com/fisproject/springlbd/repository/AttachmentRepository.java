package com.fisproject.springlbd.repository;


import com.fisproject.springlbd.entity.Attachment;
import com.fisproject.springlbd.entity.Sprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Long> {


}
