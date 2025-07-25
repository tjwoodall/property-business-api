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

package v6.retrieveForeignPropertyAnnualSubmission

import shared.controllers.validators.Validator
import shared.models.errors.MtdError
import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import v6.retrieveForeignPropertyAnnualSubmission.model.request.RetrieveForeignPropertyAnnualSubmissionRequestData

trait MockRetrieveForeignPropertyAnnualSubmissionValidatorFactory extends TestSuite with MockFactory {

  val mockRetrieveForeignPropertyAnnualSubmissionValidatorFactory: RetrieveForeignPropertyAnnualSubmissionValidatorFactory =
    mock[RetrieveForeignPropertyAnnualSubmissionValidatorFactory]

  object MockedRetrieveForeignPropertyAnnualSubmissionValidatorFactory {

    def validator(): CallHandler[Validator[RetrieveForeignPropertyAnnualSubmissionRequestData]] =
      (mockRetrieveForeignPropertyAnnualSubmissionValidatorFactory.validator(_: String, _: String, _: String)).expects(*, *, *)

  }

  def willUseValidator(use: Validator[RetrieveForeignPropertyAnnualSubmissionRequestData])
      : CallHandler[Validator[RetrieveForeignPropertyAnnualSubmissionRequestData]] = {
    MockedRetrieveForeignPropertyAnnualSubmissionValidatorFactory
      .validator()
      .anyNumberOfTimes()
      .returns(use)
  }

  def returningSuccess(result: RetrieveForeignPropertyAnnualSubmissionRequestData): Validator[RetrieveForeignPropertyAnnualSubmissionRequestData] =
    new Validator[RetrieveForeignPropertyAnnualSubmissionRequestData] {
      def validate: Validated[Seq[MtdError], RetrieveForeignPropertyAnnualSubmissionRequestData] = Valid(result)
    }

  def returning(result: MtdError*): Validator[RetrieveForeignPropertyAnnualSubmissionRequestData] = returningErrors(result)

  def returningErrors(result: Seq[MtdError]): Validator[RetrieveForeignPropertyAnnualSubmissionRequestData] =
    new Validator[RetrieveForeignPropertyAnnualSubmissionRequestData] {
      def validate: Validated[Seq[MtdError], RetrieveForeignPropertyAnnualSubmissionRequestData] = Invalid(result)
    }

}
