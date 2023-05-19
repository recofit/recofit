package site.recofit.ssafit.repository;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Video;

import java.util.List;

@Repository
public interface VideoDao {
    void save(final Video video);

    Video findById(final String videoId);

    void subscribe(final String memberId, final String videoId);

    void unsubscribe(final String memberId, final String videoId);

    List<Video> findByUserId(final String memberId);
}
