using FluentAssertions;
using SimpleCalculator.Business;
using SimpleCalculator.Business.Enums;
using SimpleCalculator.Business.OperatorBusiness;
using SimpleCalculator.Business.OperatorBusiness.Operators;

namespace SimpleCalculator.Test;

public class UnitTest
{
    private readonly Calculator _calculator;
    private readonly OperatorProvider _operatorProvider;

    public UnitTest()
    {
        _operatorProvider = new OperatorProvider();
        _calculator = new Calculator();
    }


    [Theory]
    [InlineData(33, 77, 110)]
    [InlineData(60, 9, 69)]
    public void CalculateSum_TwoIntegers_SumOfTwoNumbers(int firstNum, int secondNum, int expected)
    {
        var result = _calculator.Calculate(firstNum, secondNum, OperatorEnum.sum);
        result.Should().Be(expected);
    }

    [Theory]
    [InlineData(110, 77, 33)]
    [InlineData(123, 23, 100)]
    public void CalculateSub_TwoIntegers_SubOfTwoNumbers(int firstNum , int secondNum, int  expected)
    {
        var result = _calculator.Calculate(firstNum, secondNum, OperatorEnum.sub);
        result.Should().Be(expected);
    }

    [Theory]
    [InlineData(1, 22, 22)]
    [InlineData(0, -3244245, 0)]
    [InlineData(12, 12, 144)]
    public void CalculateMultiplication_TwoIntegers_MultiplicationOfTwoNumbers( int firstNum, int secondNum, int expected)
    {
        var result = _calculator.Calculate(firstNum, secondNum, OperatorEnum.multiply);
        result.Should().Be(expected);
    }

    [Theory]
    [InlineData(33, 3, 11)]
    [InlineData(-50, 10, -5)]
    public void CalculateDivision_TwoIntegers_DivisionOfTwoNumbers( int firstNum, int secondNum, int expected)
    {
        var result = _calculator.Calculate(firstNum, secondNum, OperatorEnum.division);
        result.Should().Be(expected);
    }


    [Theory]
    [InlineData(32)]
    public void CalculateDivision_DivideByZero_Exception(int firstNum)
    {
        _calculator.Invoking(c => c.Calculate(firstNum, 0, OperatorEnum.division))
            .Should().Throw<DivideByZeroException>();

    }

    [Fact]
    public void GetOperator_InvalidOperator_Exception()
    {
        _operatorProvider.Invoking(o => o.GetOperator((OperatorEnum)(-1))).Should().Throw<NotSupportedException>();

    }
} 

