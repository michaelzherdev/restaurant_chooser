FROM postgres

LABEL Author="Mikhail Zherdev", Version=1.0

ENV POSTGRES_USER user \
        POSTGRES_PASSWORD user \
        POSTGRES_DB restaurant

#ADD src/main/resources/docker/init.sh /docker-entrypoint-initdb.d/
