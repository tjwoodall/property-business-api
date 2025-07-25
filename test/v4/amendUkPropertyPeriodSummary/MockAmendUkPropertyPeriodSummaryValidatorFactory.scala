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

package v4.amendUkPropertyPeriodSummary

import shared.controllers.validators.Validator
import shared.models.errors.MtdError
import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import play.api.libs.json.JsValue
import v4.amendUkPropertyPeriodSummary.model.request.AmendUkPropertyPeriodSummaryRequestData

trait MockAmendUkPropertyPeriodSummaryValidatorFactory extends TestSuite with MockFactory {

  val mockAmendUkPropertyPeriodSummaryValidatorFactory: AmendUkPropertyPeriodSummaryValidatorFactory =
    mock[AmendUkPropertyPeriodSummaryValidatorFactory]

  object MockedAmendUkPropertyPeriodSummaryValidatorFactory {

    def validator(): CallHandler[Validator[AmendUkPropertyPeriodSummaryRequestData]] =
      (mockAmendUkPropertyPeriodSummaryValidatorFactory.validator(_: String, _: String, _: String, _: String, _: JsValue)).expects(*, *, *, *, *)

  }

  def willUseValidator(use: Validator[AmendUkPropertyPeriodSummaryRequestData]): CallHandler[Validator[AmendUkPropertyPeriodSummaryRequestData]] = {
    MockedAmendUkPropertyPeriodSummaryValidatorFactory
      .validator()
      .anyNumberOfTimes()
      .returns(use)
  }

  def returningSuccess(result: AmendUkPropertyPeriodSummaryRequestData): Validator[AmendUkPropertyPeriodSummaryRequestData] =
    new Validator[AmendUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], AmendUkPropertyPeriodSummaryRequestData] = Valid(result)
    }

  def returning(result: MtdError*): Validator[AmendUkPropertyPeriodSummaryRequestData] = returningErrors(result)

  def returningErrors(result: Seq[MtdError]): Validator[AmendUkPropertyPeriodSummaryRequestData] =
    new Validator[AmendUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], AmendUkPropertyPeriodSummaryRequestData] = Invalid(result)
    }

}
