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

package v4.createUkPropertyPeriodSummary

import shared.controllers.validators.Validator
import shared.models.errors.MtdError
import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import play.api.libs.json.JsValue
import v4.createUkPropertyPeriodSummary.model.request.CreateUkPropertyPeriodSummaryRequestData

trait MockCreateUkPropertyPeriodSummaryValidatorFactory extends TestSuite with MockFactory {

  val mockCreateUkPropertyPeriodSummaryValidatorFactory: CreateUkPropertyPeriodSummaryValidatorFactory =
    mock[CreateUkPropertyPeriodSummaryValidatorFactory]

  object MockedCreateUkPropertyPeriodSummaryValidatorFactory {

    def validator(): CallHandler[Validator[CreateUkPropertyPeriodSummaryRequestData]] =
      (mockCreateUkPropertyPeriodSummaryValidatorFactory.validator(_: String, _: String, _: String, _: JsValue)).expects(*, *, *, *)

  }

  def willUseValidator(use: Validator[CreateUkPropertyPeriodSummaryRequestData]): CallHandler[Validator[CreateUkPropertyPeriodSummaryRequestData]] = {
    MockedCreateUkPropertyPeriodSummaryValidatorFactory
      .validator()
      .anyNumberOfTimes()
      .returns(use)
  }

  def returningSuccess(result: CreateUkPropertyPeriodSummaryRequestData): Validator[CreateUkPropertyPeriodSummaryRequestData] =
    new Validator[CreateUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], CreateUkPropertyPeriodSummaryRequestData] = Valid(result)
    }

  def returning(result: MtdError*): Validator[CreateUkPropertyPeriodSummaryRequestData] = returningErrors(result)

  def returningErrors(result: Seq[MtdError]): Validator[CreateUkPropertyPeriodSummaryRequestData] =
    new Validator[CreateUkPropertyPeriodSummaryRequestData] {
      def validate: Validated[Seq[MtdError], CreateUkPropertyPeriodSummaryRequestData] = Invalid(result)
    }

}
