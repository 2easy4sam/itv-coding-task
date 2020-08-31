#!/usr/bin/env bash
mvn clean package spring-boot:repackage && java -jar ./target/itv.jar