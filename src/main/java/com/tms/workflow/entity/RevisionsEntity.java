package com.tms.workflow.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import lombok.Data;

@Data
@Entity
@RevisionEntity
public class RevisionsEntity {

	@Id
	@GeneratedValue
	@RevisionNumber
	Long revisionId;

	@RevisionTimestamp
	Date revisionDate;

}