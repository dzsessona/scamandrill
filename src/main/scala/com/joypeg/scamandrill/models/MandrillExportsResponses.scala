package com.joypeg.scamandrill.models

/**
 * The export response
 * @param id - the unique identifier for this Export. Use this identifier when checking the export job's status
 * @param created_at - the date and time that the export job was created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param `type` - the type of the export job
 * @param finished_at - the date and time that the export job was finished as a UTC string in YYYY-MM-DD HH:MM:SS format, or null for jobs that have not run
 * @param state - the export job's state
 * @param result_url - the url for the export job's results, if the job is complete
 */
case class MExportResponse(id: String,
                           created_at: String,
                           `type`: String,
                           finished_at: Option[String],
                           state: String,
                           result_url: Option[String]) extends MandrillResponse

