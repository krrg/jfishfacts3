package fishfacts.tests;

import fishfacts.model.settings.IProblemSettings;
import fishfacts.model.settings.ops.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by krr428 on 7/5/14.
 */
public class AbstractOperatorTest
{
    @Test
    public void testSimpleFactCreation()
    {
        IProblemSettings<Integer> settings = getProblemSettings(Add.getInstance());
        ProblemTuple<Integer> problemTuple = RandomTupleBuilder.getInstance().generateProblem(settings);

        Assert.assertTrue(true); //If we made it this far without error.
    }

    @Test
    public void testSimpleFactAnswers()
    {
        IProblemSettings<Integer> settings = getProblemSettings(Add.getInstance());
        ProblemTuple<Integer> problemTuple = RandomTupleBuilder.getInstance().generateProblem(settings);

        int a = problemTuple.getOperandA();
        int b = problemTuple.getOperandB();
        int c = problemTuple.getAnswer();

        Assert.assertTrue(a + b == c);
        Assert.assertTrue(Arrays.asList(1, 2, 3).contains(a));
        Assert.assertTrue(Arrays.asList(1, 2, 3).contains(b));
        Assert.assertTrue(problemTuple.isCorrect(a + b));
    }

    @Test
    public void testSimpleSubtract()
    {
        final int NUM_ITER = 10;

        for (int i = 0; i < NUM_ITER; i++)
        {
            IProblemSettings<Integer> settings = getProblemSettings(Subtract.getInstance());
            ProblemTuple<Integer> problemTuple = RandomTupleBuilder.getInstance().generateProblem(settings);

            int a = problemTuple.getOperandA();
            int b = problemTuple.getOperandB();
            int c = problemTuple.getAnswer();

            Assert.assertTrue(c > 0);
            Assert.assertTrue(a - b == c);
            Assert.assertTrue(problemTuple.isCorrect(c));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(b));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(c));
        }

    }

    @Test
    public void testSimpleMultiply()
    {
        final int NUM_ITER = 10;

        for (int i = 0; i < NUM_ITER; i++)
        {
            IProblemSettings<Integer> settings = getProblemSettings(Multiply.getInstance());
            ProblemTuple<Integer> problemTuple = RandomTupleBuilder.getInstance().generateProblem(settings);

            int a = problemTuple.getOperandA();
            int b = problemTuple.getOperandB();
            int c = problemTuple.getAnswer();

            Assert.assertTrue(c > 0);
            Assert.assertTrue(a * b == c);
            Assert.assertTrue(problemTuple.isCorrect(c));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(a));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(b));
        }
    }

    @Test
    public void testSimpleDivide()
    {
        final int NUM_ITER = 10;

        for (int i = 0; i < NUM_ITER; i++)
        {
            IProblemSettings<Integer> settings = getProblemSettings(Divide.getInstance());
            ProblemTuple<Integer> problemTuple = RandomTupleBuilder.getInstance().generateProblem(settings);

            int a = problemTuple.getOperandA();
            int b = problemTuple.getOperandB();
            int c = problemTuple.getAnswer();

            Assert.assertTrue(c > 0);
            Assert.assertTrue(a / b == c);
            Assert.assertTrue(problemTuple.isCorrect(c));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(b));
            Assert.assertTrue(Arrays.asList(1, 2, 3).contains(c));
        }
    }

    private IProblemSettings<Integer> getProblemSettings(final AbstractOperator<Integer> operator)
    {
        return new IProblemSettings<Integer>()
        {
            @Override
            public void setAllowedOperations(Collection<AbstractOperator<Integer>> allowed)
            {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            public void addAllowedOperation(AbstractOperator<Integer> allowed)
            {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            public void removeAllowedOperation(AbstractOperator<Integer> disallowed)
            {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            public Set<AbstractOperator<Integer>> getAllowedOperations()
            {
                return new HashSet<>(Arrays.asList(operator));
            }

            @Override
            public Collection<Integer> getAllowedOperandsFor(AbstractOperator<Integer> _operator, int operandIndex)
            {
                if (! _operator.equals(operator))
                {
                    return Collections.EMPTY_LIST;
                }
                else
                {
                    return Arrays.asList(1, 2, 3);
                }
            }

            @Override
            public void setAllowedOperandsFor(AbstractOperator<Integer> operator, int operandIndex, Collection<Integer> allowedOperands)
            {
                throw new UnsupportedOperationException();
            }
        };
    }
}
