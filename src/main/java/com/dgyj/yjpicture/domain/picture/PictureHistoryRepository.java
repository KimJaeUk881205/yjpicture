package com.dgyj.yjpicture.domain.picture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureHistoryRepository extends JpaRepository<PictureHistory, Long>, PictureRepositorySupportCustom {
}
