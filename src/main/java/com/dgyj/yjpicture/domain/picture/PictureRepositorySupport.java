package com.dgyj.yjpicture.domain.picture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PictureRepositorySupport implements PictureRepositorySupportCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
