package fishfacts.model.operations;

import fishfacts.model.settings.IProblemSettings;

import java.util.*;

/**
 * Created by krr428 on 7/3/14.
 */
public class RandomTupleBuilder
{
    private static RandomTupleBuilder instance = null;
    private static Random rand = new Random();

    public static RandomTupleBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new RandomTupleBuilder();
        }
        return instance;
    }

    private RandomTupleBuilder()
    {

    }

    public ProblemTuple<Integer> generateProblem(IProblemSettings problemSettings)
    {
        AbstractOperator operator = getRandomOperation(problemSettings);
        List<Integer> operators = getRandomOperands(problemSettings, operator);

        return operator.createProblemFrom(operators.get(0), operators.get(1));
    }

    private AbstractOperator getRandomOperation(IProblemSettings<Integer> problemSettings)
    {
        List<AbstractOperator<Integer>> operators = new ArrayList<>(problemSettings.getAllowedOperations());
        Collections.shuffle(operators);
        return operators.get(0);
    }

    private List<Integer> getRandomOperands(IProblemSettings<Integer> settings, AbstractOperator operator)
    {
        List<Integer> allowedLeft = new ArrayList<>(settings.getAllowedOperandsFor(operator, 0));
        List<Integer> allowedRight = new ArrayList<>(settings.getAllowedOperandsFor(operator, 1));

        Collections.shuffle(allowedLeft);
        Collections.shuffle(allowedRight);

        if (rand.nextBoolean())
        {
            return Arrays.asList(allowedLeft.get(0), allowedRight.get(0));
        }
        else
        {
            return Arrays.asList(allowedRight.get(0), allowedLeft.get(0));
        }
    }

}
