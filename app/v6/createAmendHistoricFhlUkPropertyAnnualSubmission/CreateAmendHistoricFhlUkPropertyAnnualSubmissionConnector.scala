/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v6.createAmendHistoricFhlUkPropertyAnnualSubmission

import shared.config.SharedAppConfig
import shared.connectors.DownstreamUri.IfsUri
import shared.connectors.httpparsers.StandardDownstreamHttpParser.reads
import shared.connectors.{BaseDownstreamConnector, DownstreamOutcome}
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.http.client.HttpClientV2
import v6.createAmendHistoricFhlUkPropertyAnnualSubmission.model.request.{
  CreateAmendHistoricFhlUkPropertyAnnualSubmissionRequestData,
  Def1_CreateAmendHistoricFhlUkPropertyAnnualSubmissionRequestData
}
import v6.createAmendHistoricFhlUkPropertyAnnualSubmission.model.response.CreateAmendHistoricFhlUkPropertyAnnualSubmissionResponse

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateAmendHistoricFhlUkPropertyAnnualSubmissionConnector @Inject() (val http: HttpClientV2, val appConfig: SharedAppConfig)
    extends BaseDownstreamConnector {

  def amend(request: CreateAmendHistoricFhlUkPropertyAnnualSubmissionRequestData)(implicit
      hc: HeaderCarrier,
      ec: ExecutionContext,
      correlationId: String): Future[DownstreamOutcome[CreateAmendHistoricFhlUkPropertyAnnualSubmissionResponse]] = {

    request match {
      case def1: Def1_CreateAmendHistoricFhlUkPropertyAnnualSubmissionRequestData =>
        import def1._

        val downstreamUri = IfsUri[CreateAmendHistoricFhlUkPropertyAnnualSubmissionResponse](
          s"income-tax/nino/$nino/uk-properties/furnished-holiday-lettings/annual-summaries/${taxYear.asDownstream}")

        put(body, downstreamUri)
    }

  }

}
