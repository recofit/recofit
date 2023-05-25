package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Video;

import java.util.List;

@Repository
public interface VideoDao {
    void save(final Video video);

    Video findById(final String videoId);

    void subscribe(final String memberName, final String videoId);

    void unsubscribe(final String memberName, final String videoId);

    List<Video> findByMemberId(final String memberName);
}
