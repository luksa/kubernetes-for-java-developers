FROM hypriot/rpi-alpine-scratch
USER root
RUN apk update && \
    apk upgrade && \
    apk add bash curl bind-tools && \
    rm -rf /var/cache/apk/*
ENTRYPOINT ["sleep", "99999999"]
