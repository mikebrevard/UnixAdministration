#!/usr/bin/perl

use strict;
use warnings;

my $file = $ARGV[0] or die "First argument must be log file.\n";
my $file2 = $ARGV[1] or die "Second argument must be log file.\n";

my $numtests = 0;
my $duration = 0;

open(my $log, '<', $file) or die "Could not open '$file' $!\n";
open(my $log2, '<', $file2) or die "Could not open '$file' $!\n";

my %errors;

while (my $line = <$log>)
{
    $numtests += 1;
    my @fields = ($line =~ /IP= (\d+\.\d+\.\d+\.\d+); Date=(.+); Duration=(\d+)ms; Number of Reads=(\d+); Number of Writes=(\d+); Number of Updates=(\d+); Status=(\w+);( Message=(.+))?/);
    
    $duration += $3;
    
    if($7 eq "Fail")
    {
        $errors{$9} += 1;
    }
}

my $avg = $duration / $numtests;

print "=========================================\n";
print "Number of Tests: $numtests\n";
print "Total Test Duration: $duration ms\n";
print "Average time: $avg\n";
print "=========================================\n";
print "Error Counts\n";
while ((my $error, my $count) = each(%errors))
{
    print "-----------------------------------------\n";
    print "$error: $count\n";
}
print "=========================================\n";