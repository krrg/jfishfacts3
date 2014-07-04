package fishfacts.model.settings.ops;

import fishfacts.model.settings.IProblemSettings;

import java.util.*;

/**
 * Created by krr428 on 7/3/14.
 */
public class RandomTupleBuilder<T>
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

    public <T> ProblemTuple<T> generateProblem(IProblemSettings problemSettings)
    {
        AbstractOperator operator = getRandomOperation(problemSettings);
        List<T> operators = getRandomOperands(problemSettings, operator);

        return operator.createProblemFrom(operators.get(0), operators.get(1));
    }

    private AbstractOperator getRandomOperation(IProblemSettings problemSettings)
    {
        List<AbstractOperator> operators = new ArrayList<>(problemSettings.getAllowedOperations());
        Collections.shuffle(operators);
        return operators.get(0);
    }

    private <T> List<T> getRandomOperands(IProblemSettings settings, AbstractOperator operator)
    {
        List<T> allowedLeft = new ArrayList<>(settings.<T>getAllowedOperandsFor(operator, 0));
        List<T> allowedRight = new ArrayList<>(settings.<T>getAllowedOperandsFor(operator, 1));

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
