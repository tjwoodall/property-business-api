/*
 * Copyright 2024 HM Revenue & Customs
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

package v6.historicFhlUkPropertyPeriodSummary.create

import shared.controllers.validators.Validator
import shared.models.errors.MtdError
import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import play.api.libs.json.JsValue
import v6.historicFhlUkPropertyPeriodSummary.create.model.request.CreateHistoricFhlUkPropertyPeriodSummaryRequestData

trait MockCreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory extends TestSuite with MockFactory {

  val mockCreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory: CreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory =
    mock[CreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory]

  object MockedCreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory {

    def validator(): CallHandler[Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData]] =
      (mockCreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory.validator(_: String, _: JsValue)).expects(*, *)

  }

  def willUseValidator(use: Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData])
      : CallHandler[Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData]] = {
    MockedCreateHistoricFhlUkPropertyPeriodSummaryValidatorFactory
      .validator()
      .anyNumberOfTimes()
      .returns(use)
  }

  def returningSuccess(result: CreateHistoricFhlUkPropertyPeriodSummaryRequestData): Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData] =
    new Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], CreateHistoricFhlUkPropertyPeriodSummaryRequestData] = Valid(result)
    }

  def returning(result: MtdError*): Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData] = returningErrors(result)

  def returningErrors(result: Seq[MtdError]): Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData] =
    new Validator[CreateHistoricFhlUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], CreateHistoricFhlUkPropertyPeriodSummaryRequestData] = Invalid(result)
    }

}
