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

package v5.retrieveUkPropertyPeriodSummary

import shared.controllers.RequestContext
import shared.services.ServiceOutcome
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import v5.retrieveUkPropertyPeriodSummary.model.request.RetrieveUkPropertyPeriodSummaryRequestData
import v5.retrieveUkPropertyPeriodSummary.model.response.RetrieveUkPropertyPeriodSummaryResponse

import scala.concurrent.{ExecutionContext, Future}

trait MockRetrieveUkPropertyPeriodSummaryService extends TestSuite with MockFactory {

  val mockRetrieveUkPropertyService: RetrieveUkPropertyPeriodSummaryService = mock[RetrieveUkPropertyPeriodSummaryService]

  object MockRetrieveUkPropertyService {

    def retrieve(
        requestData: RetrieveUkPropertyPeriodSummaryRequestData): CallHandler[Future[ServiceOutcome[RetrieveUkPropertyPeriodSummaryResponse]]] = {
      (
        mockRetrieveUkPropertyService
          .retrieveUkProperty(_: RetrieveUkPropertyPeriodSummaryRequestData)(
            _: RequestContext,
            _: ExecutionContext
          )
        )
        .expects(requestData, *, *)
    }

  }

}
