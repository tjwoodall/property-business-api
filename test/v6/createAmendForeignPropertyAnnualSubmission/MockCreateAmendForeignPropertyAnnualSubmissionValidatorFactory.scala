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

package v6.createAmendForeignPropertyAnnualSubmission

import shared.controllers.validators.Validator
import shared.models.errors.MtdError
import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import play.api.libs.json.JsValue
import v6.createAmendForeignPropertyAnnualSubmission.model.request.CreateAmendForeignPropertyAnnualSubmissionRequestData

trait MockCreateAmendForeignPropertyAnnualSubmissionValidatorFactory extends TestSuite with MockFactory {

  val mockCreateAmendForeignPropertyAnnualSubmissionValidatorFactory: CreateAmendForeignPropertyAnnualSubmissionValidatorFactory =
    mock[CreateAmendForeignPropertyAnnualSubmissionValidatorFactory]

  object MockedCreateAmendForeignPropertyAnnualSubmissionValidatorFactory {

    def validator(): CallHandler[Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData]] =
      (mockCreateAmendForeignPropertyAnnualSubmissionValidatorFactory
        .validator(_: String, _: String, _: String, _: JsValue))
        .expects(*, *, *, *)

  }

  def willUseValidator(use: Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData])
      : CallHandler[Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData]] = {
    MockedCreateAmendForeignPropertyAnnualSubmissionValidatorFactory
      .validator()
      .anyNumberOfTimes()
      .returns(use)
  }

  def returningSuccess(
      result: CreateAmendForeignPropertyAnnualSubmissionRequestData): Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData] =
    new Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData] {
      def validate: Validated[Seq[MtdError], CreateAmendForeignPropertyAnnualSubmissionRequestData] = Valid(result)
    }

  def returning(result: MtdError*): Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData] = returningErrors(result)

  def returningErrors(result: Seq[MtdError]): Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData] =
    new Validator[CreateAmendForeignPropertyAnnualSubmissionRequestData] {
      def validate: Validated[Seq[MtdError], CreateAmendForeignPropertyAnnualSubmissionRequestData] = Invalid(result)
    }

}
