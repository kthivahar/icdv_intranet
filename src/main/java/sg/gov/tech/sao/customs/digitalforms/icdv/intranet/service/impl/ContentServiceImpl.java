package sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.domain.Content;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository.ContentRepository;
import sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service.ContentService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Content}.
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    private final Logger log = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content save(Content content) {
        log.debug("Request to save Content : {}", content);
        return contentRepository.save(content);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Content> findAll() {
        log.debug("Request to get all Contents");
        return contentRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Content> findOne(Long id) {
        log.debug("Request to get Content : {}", id);
        return contentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Content : {}", id);
        contentRepository.deleteById(id);
    }
}
